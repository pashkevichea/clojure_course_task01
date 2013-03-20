(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn child-links [elem]
  (map #(get (attributes %1) :href)
  (filter #(= (tag %1) :a) (children elem))))

(defn filter-class? [e] (= (get (attributes e) :class) "r"))

(defn tag? [elem] (keyword? (tag elem)))

(defn get-links []
  (let [data (parse "clojure_google.html")]
    (loop [links [] elements (list data)]
      (if (empty? elements)
        links
        (let [elem (first elements)]
          (recur (if (filter-class? elem) 
            (concat links (child-links elem)) links)
            (concat (next elements) (filter tag? (children elem)))))))))

(defn -main [] (println (str "Found " (count (get-links)) " links!")))