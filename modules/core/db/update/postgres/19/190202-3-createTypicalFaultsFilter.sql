insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$TypicalFault.browse].filter', 'Поиск типовых неисправностей',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="transportModel" class="ru.itpearls.tramservercuba.entity.TransportModel" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.transportModel.id = :component$filter.transportModel12120]]>
       <param name="component$filter.transportModel12120" javaClass="ru.itpearls.tramservercuba.entity.TransportModel">NULL</param>
     </c>
     <c name="aggregateModel" class="ru.itpearls.tramservercuba.entity.AggregateModel" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.aggregateModel.id = :component$filter.aggregateModel17277]]>
       <param name="component$filter.aggregateModel17277" javaClass="ru.itpearls.tramservercuba.entity.AggregateModel">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^