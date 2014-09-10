(ns rpg.core
  (:require [rpg.domain :refer :all]))

(declare fight generate-fight)

(def default-enemy-count 5)

(defn create-enemy []
  (get enemy-types (rand-int (count enemy-types))))

(defn generate-enemies
  ([count]
    (generate-enemies count '()))
  ([count enemies]
    (if (zero? count)
      enemies
      (generate-enemies (dec count) (conj enemies (create-enemy))))))

(defn alive? [fighter]
  (pos? (:hp fighter)))

(defn random-dmg []
  (-> player :weapon :dmg))

(defn attack [fighter msg]
  (let [dmg (random-dmg)]
    (println (str msg " " dmg "\n"))
    (if (> (:hp fighter) dmg)
      (update-in fighter [:hp] - dmg)
      nil)))

(defn attack-player [player]
  (attack player "Enemy hits you for"))

(defn attack-enemy [enemy]
  (attack enemy "You hit enemy for"))

(defn show-health [player enemy]
  (println (str "Your hp:" (:hp player) "\t" "Enemy hp:" (:hp enemy) "\n")))

(defn player-died []
  (println "You have died!\n")
  (System/exit 0))

(defn enemy-died [enemy]
  (println (str (:name enemy) " has died!\n")))

(defn player-won? [player enemies]
  (when (empty? enemies)
    (println "\n****************************************************************************")
    (println "\tCongratulations " (:name player) ", you have defeated all your enemies!")
    (println "\tYou have" (:hp player) "hp left after the battle!")
    (println "****************************************************************************\n")
    (System/exit 0)))

(defn show-fight-info [player enemy]
  (println (str "You are attacked by an " (:name enemy) "!\n"))
  (show-health player enemy))

(defn show-intro [enemies]
  (println "\nAs you enter the forest you are almost immediately attacked by multiple enemies!")
  (println "You quickly assess the enemies you are facing: ")
  (doall (map #(println "- " (:name %) " [hp:" (:hp %) "]") enemies))
  (println "Enemies start to surround you - be prepared!\n"))

(defn fight [player enemy]
  (println "Press any key to attack the enemy")
  (read-line)
  (if-let [enemy (attack-enemy enemy)]
    (if-let [player (attack-player player)]
      (do
        (show-health player enemy)
        (println "---------------------------------------------\n")
        (recur player enemy))
      (player-died))
    (do
      (enemy-died enemy)
      player)))

(defn start-fight [player enemies]
  (println "=============================================")
  (player-won? player enemies)
  (let [enemy (first enemies)]
    (show-fight-info player enemy)
    (if-let [player (fight player enemy)]
      (recur player (pop enemies)))))

(defn run-game [enemy-count]
  (let [enemies (generate-enemies enemy-count)]
    (show-intro enemies)
    (start-fight player enemies)))

(defn get-enemy-count [args]
  (let [enemy-count (first args)]
    (if (nil? enemy-count)
      default-enemy-count
      (Integer/parseInt enemy-count))))

(defn -main [& args]
  (run-game (get-enemy-count args)))
