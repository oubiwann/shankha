(defproject clojusc/shankha "0.2.0-SNAPSHOT"
  :description "A Divine Shell"
  :url "https://github.com/clojusc/shankha"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :aot [clojusc.shankha]
  :main clojusc.shankha
  :repl-options
    {:welcome (println "Welcome to Shankha, a Clojure shell.")
     :init (clojusc.shankha/-main)
     :prompt (fn [ns]
               (let [light-green "\33[1;32;40m"
                     dark-green "\33[0;32;40m"
                     red "\33[0;31;40m"
                     blue "\33[1;34;40m"
                     dark-yellow "\33[0;33;40m"
                     end-color "\33[m"]
                 (str light-green
                      (clojure.string/trim-newline
                        ((clojure.java.shell/sh "bash" :in "echo $USER") :out))
                      "@"
                      end-color
                      red
                      (clojure.string/trim-newline
                        ((clojure.java.shell/sh "hostname" "-s") :out))
                      end-color
                      " "
                      blue
                      ((clojure.java.shell/sh "pwd") :out)
                      end-color
                      dark-green
                      (clojure.string/trim-newline
                        ((clojure.java.shell/sh "date" "+%a %d %b %H:%M:%S ") :out))
                      end-color
                      blue
                      "(" ns ")"
                      end-color
                      dark-yellow
                      " => "
                      end-color)))}
  :profiles {
    :dev {
      :dependencies [[org.clojure/tools.namespace "0.2.11"]
                     [slamhound "1.5.5"]]
      :plugins [[lein-kibit "0.1.2"]
                [jonase/eastwood "0.2.3"]]
      :aliases {"slamhound" ["run" "-m" "slam.hound"]}
      :source-paths ["dev-resources/src"]}})
