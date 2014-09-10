(ns rpg.character.enemy
  (:require [rpg.character.common :refer :all]))

(def enemy-types [{:name "Spider"
                   :hp 50
                   :weapon {:dmg 20}}
                  {:name "Blood elf"
                   :hp 100
                   :weapon {:dmg 30}}
                  {:name "Orc"
                   :hp 200
                   :weapon {:dmg 40}}
                  {:name "Fire elemental"
                   :hp 150
                   :weapon {:dmg 70}}
                  {:name "Darkhound"
                   :hp 100
                   :weapon {:dmg 100}}
                  {:name "Dreadlord"
                   :hp 400
                   :weapon {:dmg 150}}])

(defn- create []
  (get enemy-types (rand-int (count enemy-types))))

(defn create-all
  ([count]
    (create-all count '()))
  ([count enemies]
    (if (zero? count)
      enemies
      (create-all (dec count) (conj enemies (create))))))

(defn- calculate-dmg [enemy]
  (-> enemy :weapon :dmg))

(defn attack [player enemy]
  (let [dmg (calculate-dmg enemy)]
    (println "Enemy hits you for" dmg "\n")
    (if (> (:hp player) dmg)
      (update-in player [:hp] - dmg)
      nil)))