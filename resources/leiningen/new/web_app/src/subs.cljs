(ns {{name}}.subs
  (:require [re-frame.core :refer [reg-sub]]))

;; -------------------------------------------------------------------------------------
;; Layer 2: "extractors"
;;
;; https://github.com/Day8/re-frame/blob/master/docs/SubscriptionInfographic.md
;;
;; Layer 2 :: Get data from `app-db`
;; Why? Efficiency. Every Layer 2 subscription will rerun any time
;; `app-db` changes (in any way). As a result, we want Layer 2 to be trivial.

(reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))


;; -------------------------------------------------------------------------------------
;; Layer 3:: Subscription handler
;;
;; A subscription handler:: function which is re-run when its input signals
;; change. Each time it is rerun, it produces a new output (return value).
;;
;; In the simple case, app-db is the only input signal.
;; But many subscriptions are not directly dependent on app-db, and instead,
;; depend on a value derived from app-db.
;;
;; New values emanate from app-db, and flow out through a signal graph,
;; into and out of these intermediate nodes, before a leaf subscription
;; delivers data into views which render data as hiccup.
;;
;; When writing and registering the handler for an intermediate node
;; you must nominate one or more input signals (typically one or two).
;;
;; reg-sub allows you to supply:
;;
;;   1. a function which returns the input signals. Can return single signal,
;;      a vector of signals, or a map where the values are the signals.
;;
;;   2. a function which does the computation. Input values -> a new value.
;;
;; Above, we only supplied the 2nd of these functions.
;; But now we are dealing with intermediate (layer 3) nodes, we'll need to provide both fns.
