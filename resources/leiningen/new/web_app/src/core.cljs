(ns {{name}}.core
    (:require-macros [secretary.core :refer [defroute]])
    (:require [goog.events :as events]
              [reagent.dom :refer [render]]
              [re-frame.core :refer [dispatch dispatch-sync]]
              [secretary.core :as secretary]
              [{{name}}.events.core] ;; required to make the compiler
              [{{name}}.subs]   ;; load them (see docs/App-Structure.md)
              [{{name}}.views]
              ;;[devtools.core :as devtools]
              )
    (:import [goog History]
             [goog.history EventType]))

;; Used for initialization
(dispatch-sync [:initialize-db])

;; -- Debugging aids ----------------------------------------------------------
;;(devtools/install!)       ;; https://github.com/binaryage/cljs-devtools
(enable-console-print!)   ;; so that println writes to `console.log`

;; -- Routes and History ------------------------------------------------------

(defroute "/" []
  (dispatch [:set-active-panel :home]))
(defroute "/:path" [path]
  (dispatch [:set-active-panel (keyword path)]))

(def history
  (doto (History.)
    (events/listen EventType.NAVIGATE
                   (fn [event] (secretary/dispatch! (.-token event))))
    (.setEnabled true)))


;; -- Entry Point -------------------------------------------------------------
;; Within ../../resources/public/index.html you'll see this code
;;    window.onload = function () {
;;      {{name}}.core.main();
;;    }
;; So this is the entry function that kicks off the app once HTML is loaded

(defn ^:export main
  []
  ;; `{{name}}.views/app` is the root view for the entire UI.
  (render [{{name}}.views/app]
          (.getElementById js/document "app")))
