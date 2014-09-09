(ns rpg.core)

(def player-character {:name "John Doe"
                       :hp 100
                       :weapon {:name "1h sword"
                                :dmg 10}})
(def monsters [{:name "Spider" :hp 20 :dmg 5}
               {:name "Goblin" :hp 30 :dmg 10}
               {:name "Orc" :hp 40 :dmg 20}])

(defn alive? [fighter]
  (pos? (:hp fighter)))

(defn random-enemy []
  (get monsters (rand-int (count monsters))))

(defn random-dmg []
  (-> player-character :weapon :dmg))

(defn attack [enemy]
  (let [dmg (random-dmg)]
    (println (str "You hit enemy for " dmg "\n"))
    (update-in enemy [:hp] - dmg)))

(defn fight [player enemy]
  (println (str "Your hp:" (:hp player) "\t" "Enemy hp:" (:hp enemy) "\n"))
  (println "Press any key to attack the enemy")
  (read-line)
  (let [enemy (attack enemy)]
    (if (alive? enemy)
      (recur player enemy))))

(defn generate-fight [player]
  (println "-----------------------------------------------")
  (let [enemy (random-enemy)]
    (println (str "You have encountered an " (:name enemy) "!\n"))
    (fight player enemy)
    (println (str (:name enemy) " has died!\n"))))

(defn run-game []
  (let [player player-character]
    (while true
      (generate-fight player))))

(defn -main
  [& args]
  (run-game))
