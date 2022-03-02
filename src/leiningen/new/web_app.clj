(ns leiningen.new.web-app
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main]))

(def render (tmpl/renderer "web_app"))

(defn web-app
  [name]
  (let [data {:name      name
              :sanitized (tmpl/name-to-path name)}]
    (main/info "Generating fresh 'lein new' org.clojars.alai57/web-app project.")
    (tmpl/->files data
                  ["package.json" (render "package.json" data)]
                  ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
                  ["webpack.config.js" (render "webpack.config.js" data)]
                  ["README.md" (render "README.md" data)]

                  ;; Storybook
                  [".storybook/.babelrc" (render ".storybook/.babelrc" data)]
                  [".storybook/main.js" (render ".storybook/main.js" data)]
                  [".storybook/manager.js" (render ".storybook/manager.js" data)]
                  [".storybook/preview-body.html" (render ".storybook/preview-body.html" data)]
                  [".storybook/preview-head.html" (render ".storybook/preview-head.html" data)]
                  [".storybook/preview.js" (render ".storybook/preview.js" data)]

                  ;; cljs base
                  ["src/{{sanitized}}/core.cljs" (render "src/core.cljs" data)]
                  ["src/{{sanitized}}/subs.cljs" (render "src/subs.cljs" data)]
                  ["src/{{sanitized}}/db.cljs" (render "src/db.cljs" data)]

                  ;; re-frame events
                  ["src/{{sanitized}}/events/core.cljs" (render "src/events/core.cljs" data)]

                  ;; components
                  ["src/{{sanitized}}/components/input_box.cljs" (render "src/components/input_box.cljs" data)]
                  ["src/{{sanitized}}/components/primary_button.cljs" (render "src/components/primary_button.cljs" data)]
                  ["src/{{sanitized}}/components/radio_group.cljs" (render "src/components/radio_group.cljs" data)]
                  ["src/{{sanitized}}/components/secondary_button.cljs" (render "src/components/secondary_button.cljs" data)]
                  ["src/{{sanitized}}/components/side_menu.cljs" (render "src/components/side_menu.cljs" data)]
                  ["src/{{sanitized}}/components/snackbar.cljs" (render "src/components/snackbar.cljs" data)]
                  ["src/{{sanitized}}/components/thumbnail.cljs" (render "src/components/thumbnail.cljs" data)]

                  ;; stories
                  ["src/{{sanitized}}/stories/helper.cljs" (render "src/stories/helper.cljs" data)]
                  ["src/{{sanitized}}/stories/components/input_box_stories.cljs" (render "src/stories/components/input_box_stories.cljs" data)]
                  ["src/{{sanitized}}/stories/components/primary_button_stories.cljs" (render "src/stories/components/primary_button_stories.cljs" data)]
                  ["src/{{sanitized}}/stories/components/radio_group_stories.cljs" (render "src/stories/components/radio_group_stories.cljs" data)]
                  ["src/{{sanitized}}/stories/components/secondary_button_stories.cljs" (render "src/stories/components/secondary_button_stories.cljs" data)]
                  ["src/{{sanitized}}/stories/components/side_menu_stories.cljs" (render "src/stories/components/side_menu_stories.cljs" data)]
                  ["src/{{sanitized}}/stories/components/thumbnail_stories.cljs" (render "src/stories/components/thumbnail_stories.cljs" data)]
                  )))
