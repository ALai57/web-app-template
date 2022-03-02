(ns {{name}}.events.core
    (:require [{{name}}.db :refer [default-db]]
              [re-frame.core :refer [dispatch reg-event-db]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Helper functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(reg-event-db
 :initialize-db
 (fn [_ _]
   default-db))

