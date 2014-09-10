(ns rpg.engine
  (:require [rpg.character.player :as player]
            [rpg.character.enemy :as enemy]
            [rpg.ui :as ui]))

(def default-enemy-count 5)

(defn close-game [] (System/exit 0))

(defn player-won? [enemies]
  (empty? enemies))

(defn fight-enemy [player enemy]
  (ui/press-any-key)
  (read-line)
  (if-let [enemy (player/attack player enemy)]
    (if-let [player (enemy/attack player enemy)]
      (do
        (ui/show-health player enemy)
        (ui/show-attack-divider)
        (recur player enemy))
      (do
        (ui/player-died)
        (close-game)))
    (do
      (ui/enemy-died enemy)
      player)))

(defn fight-enemies [player enemies]
  (ui/show-fight-divider)
  (when (player-won? enemies)
    (ui/player-won player)
    (close-game))
  (let [enemy (first enemies)]
    (ui/show-fight-info player enemy)
    (if-let [player (fight-enemy player enemy)]
      (recur player (pop enemies)))))

(defn run-game [enemy-count]
  (let [enemies (enemy/create-all enemy-count)]
    (ui/show-intro enemies)
    (fight-enemies player/player enemies)))

(defn get-enemy-count [args]
  (let [enemy-count (first args)]
    (if (nil? enemy-count)
      default-enemy-count
      (Integer/parseInt enemy-count))))

(defn -main [& args]
  (run-game (get-enemy-count args)))
