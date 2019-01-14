insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$Repair.edit].filter', 'Поиск по истории ремонтов',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="startDate" class="java.util.Date" operatorType="GREATER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.startDate >= :component$filter.startDate31542]]>
       <param name="component$filter.startDate31542" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="finishDate" class="java.util.Date" operatorType="LESSER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.finishDate <= :component$filter.finishDate77578]]>
       <param name="component$filter.finishDate77578" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="maintenanceKind" class="ru.itpearls.tramservercuba.entity.MaintenanceKind" inExpr="true" operatorType="NOT_IN" width="1" type="PROPERTY"><![CDATA[((e.maintenanceKind.id not in :component$filter.maintenanceKind05477) or (e.maintenanceKind is null)) ]]>
       <param name="component$filter.maintenanceKind05477" javaClass="ru.itpearls.tramservercuba.entity.MaintenanceKind">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^