#!/usr/bin/env bb

(ns input.hearings.get-hearings-test
  (:require [cheshire.core :as json])
  (:import [java.time ZonedDateTime ZoneId]
           [java.time.format DateTimeFormatter]))

(def JSON_FILE "data/hearings-action-test.json")

(defn- singapore-timestamp []
  (.format (ZonedDateTime/now (ZoneId/of "Asia/Singapore"))
           DateTimeFormatter/ISO_OFFSET_DATE_TIME))

(defn -main []
  (spit JSON_FILE
        (json/generate-string
         [{:title "GitHub Actions commit/push test"
           :link "https://github.com"
           :type "workflow-test"
           :reference "hearing-input-workflow"
           :timestamp (singapore-timestamp)
           :venue "GitHub Actions"
           :coram "Automated test"}])))
