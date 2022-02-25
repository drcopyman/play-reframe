(ns play-reframe.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::loading
 (fn [db]
   (:loading db)))

(re-frame/reg-sub
 ::users
 (fn [db]
   (:users db)))