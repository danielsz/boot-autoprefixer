(set-env!
  :source-paths #{"src"}
  :dependencies '[[boot/core "2.0.0-rc13" :scope "provided"]
                  [adzerk/bootlaces "0.1.12" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.0.3")
(bootlaces! +version+)

(task-options!
 aot {:namespace '#{danielsz.autoprefixer}}
 pom {:project 'danielsz/boot-autoprefixer
      :version +version+
      :scm {:name "git"
            :url "https://github.com/danielsz/boot-autoprefixer"}})

(deftask build
  "Build jar and install to local repo."
  []
  (comp (aot) (pom) (jar) (install)))
