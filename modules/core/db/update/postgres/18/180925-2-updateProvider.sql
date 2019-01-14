alter table TRAMSERVERCUBA_PROVIDER add column DELIVER_SERVICES boolean ^

update SEC_FILTER set XML =
'<?xml version="1.0" encoding="UTF-8"?>
 <filter>
   <and>
     <c name="name" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.name like :component$filter.name36897 ESCAPE ''\'' ]]>
       <param name="component$filter.name36897" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="code" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.code like :component$filter.code37483 ESCAPE ''\'' ]]>
       <param name="component$filter.code37483" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="address" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.address like :component$filter.address21482 ESCAPE ''\'' ]]>
       <param name="component$filter.address21482" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="deliverTransport" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverTransport = :component$filter.deliverTransport38270]]>
       <param name="component$filter.deliverTransport38270" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverAggregates" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverAggregates = :component$filter.deliverAggregates20935]]>
       <param name="component$filter.deliverAggregates20935" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverAccessories" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverAccessories = :component$filter.deliverAccessories09585]]>
       <param name="component$filter.deliverAccessories09585" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverMaterials" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverMaterials = :component$filter.deliverMaterials09210]]>
       <param name="component$filter.deliverMaterials09210" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverServices" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverServices = :component$filter.deliverServices18548]]>
       <param name="component$filter.deliverServices18548" javaClass="java.lang.Boolean">NULL</param>
     </c>
   </and>
 </filter>
'
where COMPONENT = '[tramservercuba$Provider.browse].filter'
and NAME = 'Поиск поставщиков' ^
