(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"src"})

(task-options!
 pom {:project 'danielsz/boot-autoprefixer
      :version "0.0.9"
      :scm {:name "git"
            :url "https://github.com/danielsz/boot-autoprefixer"}})

(deftask build
  []
  (comp (pom) (jar) (install)))

(deftask push-release
  []
  (comp
   (build)
   (push :repo "clojars")))
