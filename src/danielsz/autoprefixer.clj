(ns danielsz.autoprefixer
  {:boot/export-tasks true}
  (:require
   [clojure.java.io :as io]
   [boot.pod        :as pod]
   [boot.core       :as core]
   [boot.util       :as util]
   [boot.tmpdir     :as tmpd]))

(defn- find-css-files [fs files]
  (->> fs
       core/input-files
       (core/by-name files)
       (map (juxt core/tmp-path core/tmp-file identity))))

(core/deftask autoprefixer
  [p exec-path PATH        str   "PostCSS executable path (defaults to \"postcss\" if omitted)."
   f files     FILES       [str] "A vector of filenames to process with autoprefixer."
   b browsers  BROWSERS    str   "A string describing browsers autoprefixer will target.
   Eg:  \"last 1 version, > 5%\".
   More details at https://github.com/ai/browserslist"]
  (let [tmp-dir (core/tmp-dir!)]
    (core/with-pre-wrap fileset
      (doseq [[in-path in-file file] (find-css-files fileset files)]
        (boot.util/info "Autoprefixing %s\n" (:path file))
        (let [out-file (doto (io/file tmp-dir in-path) io/make-parents)
              postcss (or exec-path "postcss")]
          (apply util/dosh postcss "--use" "autoprefixer"
                 (.getPath in-file)
                 "-o" (.getPath out-file)
                 (when browsers ["--autoprefixer.browsers" browsers]))))
      (-> fileset
          (core/add-resource tmp-dir)
          core/commit!))))
