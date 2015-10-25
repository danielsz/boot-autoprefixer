(set-env!
  :source-paths #{"src"}
  :dependencies '[[adzerk/bootlaces "0.1.12" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.0.5-SNAPSHOT")
(bootlaces! +version+)

(task-options!
 aot {:namespace '#{danielsz.autoprefixer}}
 pom {:project 'danielsz/boot-autoprefixer
      :version +version+
      :scm {:name "git"
            :url "https://github.com/danielsz/boot-autoprefixer"}})


