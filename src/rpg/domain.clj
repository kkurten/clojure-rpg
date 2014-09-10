(ns rpg.domain)

(def player {:name "John Doe"
             :hp 1000
             :weapon {:name "1h sword"
                      :dmg 100}})

(def enemy-types [{:name "Spider" :hp 50 :dmg 20}
                  {:name "Blood elf" :hp 100 :dmg 30}
                  {:name "Orc" :hp 200 :dmg 40}
                  {:name "Fire elemental" :hp 150 :dmg 70}
                  {:name "Darkhound" :hp 100 :dmg 100}
                  {:name "Dreadlord" :hp 400 :dmg 150}])