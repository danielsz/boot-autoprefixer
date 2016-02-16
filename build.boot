(task-options!
 pom {:project 'danielsz/boot-autoprefixer
      :version "0.5.1"
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



