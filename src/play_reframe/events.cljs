(ns play-reframe.events
  (:require
   [re-frame.core :as re-frame]
   [play-reframe.db :as db]
   [day8.re-frame.http-fx]
   [ajax.core :as ajax]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-fx
 ::fetch-users
 (fn [{:keys [db]} _]
   {:db   (assoc db :loading true)
    :http-xhrio {:method          :get
                 :uri             "https://reqres.in/api/users?page=2"
                 :timeout         8000
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success      [::fetch-users-success]
                 :on-failure      [:bad-http-result]}}))

(re-frame/reg-event-db
 ::fetch-users-success
 (fn [db [_ {:keys [data]}]]
   (-> db
       (assoc :loading false)
       (assoc :users data))))