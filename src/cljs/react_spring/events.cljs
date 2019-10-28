(ns react-spring.events
  (:require
   [re-frame.core :as rf]
   [react-spring.db :as db]
   ))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db
  :update-position
  (fn [{:keys [position] :as db} _]
    (assoc db :position (if (= position :left)
                          :right
                          :left))))