update DASHBOARD_PERSISTENT_DASHBOARD set DASHBOARD_MODEL = '
{
  "title": "MainTsDashboard",
  "code": "MainTsDashboard",
  "visualModel": {
    "children": [
      {
        "className": "com.haulmont.addon.dashboard.model.visualmodel.HorizontalLayout",
        "data": {
          "children": [
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "Online",
                  "caption": "Online",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "onlineTsWidget"
                        }
                      },
                      "id": "87384fe1-e3cf-bbf1-8dce-612e7d749a32",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "Количество транспорта на линии"
                        }
                      },
                      "id": "b53437f1-126c-e951-5a4f-4cbffe32b3fb",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "6f33c281-185c-ddc3-3c00-a1b33e488547",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "04e20489-4c89-5d72-0412-7c33d0fa3e9f",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "Repair",
                  "caption": "Repair",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "repairTsWidget"
                        }
                      },
                      "id": "64fb2f1f-84b2-694e-4e7f-0f8e0b3ae423",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "На данный момент в ремонте"
                        }
                      },
                      "id": "78c4e539-ac2b-69d0-b4eb-24706c02364c",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "2ab92238-c968-536d-8b4e-2559e8a63a9f",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "9364ff6e-2bed-ac68-ba8d-8f18126e0ddc",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "KTD",
                  "caption": "KTD",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "KTDWidget"
                        }
                      },
                      "id": "b1cb383f-2b68-1fba-eb3d-e165a07acdc9",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "коэффициент транспортной доступности"
                        }
                      },
                      "id": "e6795996-a054-aa2d-c5e6-0c1761dfa716",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "ce7490de-7bc5-0709-773a-b17ec6d474db",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "3dfe7d6b-5748-b89d-56b6-ecbfc6b3bd77",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "CrashWidget",
                  "caption": "CrashWidget",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "CrashWidget"
                        }
                      },
                      "id": "825c8cb2-b790-62fe-4c9a-b280e3983c2c",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "Статистика ДТП за 2018 год"
                        }
                      },
                      "id": "3373508a-659c-f128-e818-a5eeb0c7286c",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "c2af17c9-be8e-aa33-18b7-42ce86c5e5ed",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "9f1e1d6c-7214-f484-d066-03366f3b3cdd",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            }
          ],
          "weight": 1,
          "expand": null,
          "styleName": null,
          "width": 100,
          "height": 160,
          "widthUnit": "%",
          "heightUnit": "px",
          "id": "0f32ab60-4d33-3f70-36c1-5d8da5884b69",
          "__new": true,
          "__detached": false,
          "__removed": false,
          "__securityState": null,
          "dynamicAttributes": null,
          "_persistence_fetchGroup": null
        }
      },
      {
        "className": "com.haulmont.addon.dashboard.model.visualmodel.HorizontalLayout",
        "data": {
          "children": [
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "repairControl",
                  "caption": "Контроль ремонтов",
                  "name": "RepairControlWidget",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard$RepairControlWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "d3285dc7-4410-1250-ae5b-1c2015690589",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-container panel-table panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "38bdb50f-0246-aaee-f8cf-ff26ee1f2317",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "InRepair",
                  "caption": "В ремонте",
                  "name": "Average trip time",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$AverageTime",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "3ee0bce1-4def-0ea8-7186-a62010e062e8",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-container panel-table panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "053c2a45-8133-b61d-683d-89f03c026778",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            }
          ],
          "weight": 1,
          "expand": null,
          "styleName": null,
          "width": 100,
          "height": 100,
          "widthUnit": "%",
          "heightUnit": "%",
          "id": "d1bdd755-b783-8a92-3f23-e33bcde52c81",
          "__new": true,
          "__detached": false,
          "__removed": false,
          "__securityState": null,
          "dynamicAttributes": null,
          "_persistence_fetchGroup": null
        }
      },
      {
        "className": "com.haulmont.addon.dashboard.model.visualmodel.HorizontalLayout",
        "data": {
          "children": [
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "tsRepairTableWidget",
                  "caption": "tsRepairTableWidget",
                  "name": "TsRepairTableWidget",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard$TsRepairTableWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "2152b332-dff0-3613-4ddc-54df07bd65e3",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-container panel-table panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "41f8d184-3176-e0de-e903-04a75a8a10c7",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "test",
                  "caption": "test",
                  "name": "Car delivery",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$CarDelivery",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "bfa2e398-592d-67c4-f724-8c8161ab1884",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "0a46a409-c45e-1e7e-8f87-5a3b572b8b41",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            }
          ],
          "weight": 1,
          "expand": null,
          "styleName": null,
          "width": 100,
          "height": 100,
          "widthUnit": "%",
          "heightUnit": "%",
          "id": "ed128da8-e9a8-2edf-7b50-d111b35b6075",
          "__new": true,
          "__detached": false,
          "__removed": false,
          "__securityState": null,
          "dynamicAttributes": null,
          "_persistence_fetchGroup": null
        }
      }
    ],
    "weight": 1,
    "expand": null,
    "styleName": null,
    "width": 100,
    "height": 100,
    "widthUnit": "%",
    "heightUnit": "%",
    "id": "2c8db479-6db3-f0a6-01a3-3c24e26b0c7e",
    "__new": true,
    "__detached": false,
    "__removed": false,
    "__securityState": null,
    "dynamicAttributes": null,
    "_persistence_fetchGroup": null
  },
  "parameters": [],
  "isAvailableForAllUsers": true,
  "createdBy": "admin",
  "timerDelay": 5,
  "assistantBeanName": null,
  "id": "d3343a15-ad65-c4c3-0a5d-376fcfd8a352",
  "__new": true,
  "__detached": false,
  "__removed": false,
  "__securityState": null,
  "dynamicAttributes": null,
  "_persistence_fetchGroup": null
}
' where ID = '87399dae-47d9-1b26-849b-fd80cf0886a0' ^