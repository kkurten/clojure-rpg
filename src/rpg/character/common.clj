(ns rpg.character.common)

(defn alive? [character]
  (pos? (:hp character)))

(defn random-dmg [character]
  (-> character :weapon :dmg))
