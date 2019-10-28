(ns react-spring.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [react-spring.subs :as subs]
            ["react-spring/renderprops.js" :as rspring]))

(defn spring
  "Creates a spring animation object.
  The first parameter are its attributes.
  The second parameter is an arbitrary Reagent component.
  The third parameter is a unary props function."
  [attr component input-fn]
  [:> rspring/Spring attr
   (fn [props] (-> #(-> % (js->clj :keywordize-keys true)
                        component)
                   r/reactify-component
                   (r/create-element (clj->js (input-fn (js->clj props :keywordize-keys true))))))])

(defn unit
  [{:keys [left]}]
  [:div.unit {:on-click #(rf/dispatch [:update-position])
              :style    {:top  100
                         :left left}}
   [:div.title "Title of unit"]
   [:div.content
    [:div "Click this unit to move it."]
    [:img {:src    "/img/Clojure_logo.png"
           :width  300
           :height 300}]]
   ])

(defn main-panel []
  (let [position @(rf/subscribe [:position])]
    (spring {:to {:left (if (= position :left)
                          100
                          500)}}
            unit
            identity)))