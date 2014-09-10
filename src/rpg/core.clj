(ns rpg.core)

(declare fight generate-fight)

(def player {:name "John Doe"
             :hp 100
             :weapon {:name "1h sword"
                      :dmg 10}})
(def enemies [{:name "Spider" :hp 20 :dmg 5}
              {:name "Goblin" :hp 30 :dmg 10}
              {:name "Orc" :hp 40 :dmg 20}])

(defn alive? [fighter]
  (pos? (:hp fighter)))

(defn random-enemy []
  (get enemies (rand-int (count enemies))))

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

(defn do-player-attack [player enemy]
  (if-let [player (attack-player player)]
    (fight player enemy)
    (do
      (println "You have died!\n")
      (System/exit 0))))

(defn attack-eachother [player enemy]
  (if-let [enemy (attack-enemy enemy)]
    (do-player-attack player enemy))
  (do
    (println (str (:name enemy) " has died!\n"))
    (generate-fight player)))

(defn fight [player enemy]
  (println (str "Your hp:" (:hp player) "\t" "Enemy hp:" (:hp enemy) "\n"))
  (println "Press any key to attack the enemy")
  (read-line)
  (attack-eachother player enemy))

(defn generate-fight [player]
  (println "-----------------------------------------------")
  (let [enemy (random-enemy)]
    (println (str "You have encountered an " (:name enemy) "!\n"))
    (fight player enemy)))

(defn run-game []
  (generate-fight player))

(defn -main
  [& args]
  (run-game))
