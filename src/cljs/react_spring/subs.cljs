(ns react-spring.subs
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub
 :position
 (fn [db]
   (:position db)))
