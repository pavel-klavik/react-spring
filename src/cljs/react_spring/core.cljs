(ns react-spring.core
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [react-spring.events :as events]
   [react-spring.views :as views]
   [react-spring.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (r/render [views/main-panel]
            (.getElementById js/document "app")))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
