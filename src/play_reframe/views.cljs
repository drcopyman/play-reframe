(ns play-reframe.views
  (:require
   [re-frame.core :as re-frame]
   [play-reframe.subs :as subs]
   [play-reframe.events :as events]
   ))

(defn display-user [{:keys [id avatar email] first-name :first_name}]
  [:div.horizontal {:key id}
   [:img.pr-15 {:src avatar}]
   [:div
    [:h2 first-name]
    [:p  (str "(" email ")")]]])

(defn main-panel []
  (let [loading (re-frame/subscribe [::subs/loading])
        users (re-frame/subscribe [::subs/users])]
    [:div
     (when @loading "Loading...")
     (map display-user @users)
     [:button {:on-click #(re-frame/dispatch [::events/fetch-users])} "Call API"]]))