(ns rpg.character.player
  (:require [rpg.character.common :refer :all]))

(def player {:name "John Doe"
             :hp 1000
             :weapon {:name "1h sword"
                      :dmg 100}})

(defn- calculate-dmg [player]
  (-> player :weapon :dmg))

(defn attack [player enemy]
  (let [dmg (calculate-dmg player)]
    (println "You hit enemy for" dmg "\n")
    (if (> (:hp enemy) dmg)
      (update-in enemy [:hp] - dmg)
      nil)))