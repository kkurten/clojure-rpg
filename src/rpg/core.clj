(ns rpg.core
  (:gen-class))

(def player {
              :name "dzerome"
              :hp 100
              :weapon {
                        :name "1h sword"
                        :dmg 10}
              }
  )
(def monsters [{:name "Spider" :hp 5 :dmg 5}
               {:name "Goblin" :hp 10 :dmg 10}
               {:name "Orc" :hp 20 :dmg 20}])

(def enemy nil)

(defn player-alive? [] (pos? (:hp player)))

(defn enemy-is-alive? []
  (pos? (:hp enemy)))

(defn take-dmg [dmg]
  (def player (update-in player [:hp] - dmg)))

(defn random-enemy []
  (get monsters (rand-int (count monsters))))

(defn random-dmg []
  (-> player :weapon :dmg))

(defn attack []
  (let [dmg (random-dmg)]
    (println (str "You hit enemy for " dmg "\n"))
    (def enemy (update-in enemy [:hp] - dmg))))

(defn fight []
  (println (str "Your hp:" (:hp player) "\t" "Enemy hp:" (:hp enemy) "\n"))
  (println "Press any key to attack the enemy")
  (read-line)
  (attack))

(defn random-fight []
  (def enemy (random-enemy))
  (println (str "You have encountered an " (:name enemy) "!\n"))
  (while (enemy-is-alive?) (fight))
  (println (str (:name enemy) " has died!\n"))
  (def enemy nil)
  (println "-----------------------------------------------"))

(defn run-game []
  (while true
    (random-fight)))

(defn -main
  [& args]
  (run-game)
  )
