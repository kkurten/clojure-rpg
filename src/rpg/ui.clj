(ns rpg.ui)

(defn show-intro [enemies]
  (println "\nAs you enter the forest you are almost immediately attacked by multiple enemies!")
  (println "You quickly assess the enemies you are facing: ")
  (doall (map #(println "- " (:name %) " [hp:" (:hp %) "]") enemies))
  (println "Enemies start to surround you - be prepared!\n"))

(defn player-won [player]
  (println "\n****************************************************************************")
  (println "\tCongratulations "(:name player)", you have defeated all your enemies!")
  (println "\tYou have "(:hp player)" hp left after the battle!")
  (println "****************************************************************************\n"))

(defn player-died []
  (println "********** You have died! **********\n"))

(defn enemy-died [enemy]
  (println (str (:name enemy) " has died!\n")))

(defn show-health [player enemy]
  (println (str "Your hp:" (:hp player) "\t" "Enemy hp:" (:hp enemy) "\n")))

(defn show-fight-info [player enemy]
  (println (str "You are attacked by an " (:name enemy) "!\n"))
  (show-health player enemy))

(defn press-any-key []
  (println "Press any key to attack the enemy"))

(defn show-attack-divider []
  (println "---------------------------------------------\n"))

(defn show-fight-divider []
  (println "============================================="))