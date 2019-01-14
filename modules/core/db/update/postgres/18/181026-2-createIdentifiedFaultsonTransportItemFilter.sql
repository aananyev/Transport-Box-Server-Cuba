insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$TransportItem.edit].identifiedFaultsFilter', 'Поиск неисправностей',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="state" class="ru.itpearls.tramservercuba.entity.IdentifiedFaultsState" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.state = :component$identifiedFaultsFilter.state76816]]>
       <param name="component$identifiedFaultsFilter.state76816" javaClass="ru.itpearls.tramservercuba.entity.IdentifiedFaultsState">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^
