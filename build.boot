(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"src"})

(task-options!
 push {:repo-map {:url "https://clojars.org/repo/"}}
 pom {:project 'danielsz/boot-autoprefixer
      :version "0.1.0"
      :scm {:name "git"
            :url "https://github.com/danielsz/boot-autoprefixer"}})

(deftask build
  []
  (comp (pom) (jar) (install)))

(deftask push-release
  []
  (comp
   (build)
   (push)))
