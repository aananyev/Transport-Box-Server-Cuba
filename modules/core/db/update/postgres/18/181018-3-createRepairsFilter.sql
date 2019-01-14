insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$Repair.browse].filter', 'Поиск ремонтов',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="startDate" class="java.util.Date" operatorType="GREATER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.startDate >= :component$filter.startDate22565]]>
       <param name="component$filter.startDate22565" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="finishDate" class="java.util.Date" operatorType="LESSER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.finishDate <= :component$filter.finishDate97900]]>
       <param name="component$filter.finishDate97900" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="depo" class="ru.itpearls.tramservercuba.entity.Depo" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.depo.id = :component$filter.depo58523]]>
       <param name="component$filter.depo58523" javaClass="ru.itpearls.tramservercuba.entity.Depo">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^