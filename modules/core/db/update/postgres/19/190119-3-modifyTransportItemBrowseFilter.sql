update SEC_FILTER
set XML = '<?xml version="1.0" encoding="UTF-8"?>
 <filter>
   <and>
     <c name="factoryNumber" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.factoryNumber like :component$filter.factoryNumber23021 ESCAPE ''\'' ]]>
       <param name="component$filter.factoryNumber23021" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="workNumber" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.workNumber like :component$filter.workNumber06901 ESCAPE ''\'' ]]>
       <param name="component$filter.workNumber06901" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="model.type" class="ru.itpearls.tramservercuba.entity.TransportType" caption="msg://filterTypeLabel" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.model.type.id = :component$filter.model_type44399]]>
       <param name="component$filter.model_type44399" javaClass="ru.itpearls.tramservercuba.entity.TransportType">NULL</param>
     </c>
     <c name="model.name" class="java.lang.String" caption="msg://filterModelLabel" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.model.name like :component$filter.model_name88471 ESCAPE ''\'' ]]>
       <param name="component$filter.model_name88471" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="model.modification" class="java.lang.String" caption="msg://filterModificationLabel" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.model.modification like :component$filter.model_modification90596 ESCAPE ''\'' ]]>
       <param name="component$filter.model_modification90596" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="provider" class="ru.itpearls.tramservercuba.entity.Provider" paramWhere="{E}.deliverTransport = true" inExpr="true" operatorType="IN" width="1" type="PROPERTY"><![CDATA[e.provider.id in :component$filter.provider14174]]>
       <param name="component$filter.provider14174" javaClass="ru.itpearls.tramservercuba.entity.Provider">NULL</param>
     </c>
     <c name="depo" class="ru.itpearls.tramservercuba.entity.Depo" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.depo.id = :component$filter.depo50518]]>
       <param name="component$filter.depo50518" javaClass="ru.itpearls.tramservercuba.entity.Depo">NULL</param>
     </c>
     <c name="workStateOnLine" caption="msg://workStateOnLine" unary="true" width="1" type="CUSTOM" entityAlias="e"><![CDATA[{E}.id = r.transportItem.id and r.state = 3]]>
        <param name="component$filter.workStateOnLine16570" javaClass="java.lang.Boolean">NULL</param>
        <join><![CDATA[ tramservercuba$Repair r ]]></join>
     </c>
     <c name="workStateOnRepair" caption="msg://workStateOnRepair" unary="true" width="1" type="CUSTOM" entityAlias="e"><![CDATA[{E}.id = r.transportItem.id and r.state in (1, 2, 4)]]>
        <param name="component$filter.workStateOnRepair18934" javaClass="java.lang.Boolean">NULL</param>
        <join><![CDATA[ tramservercuba$Repair r ]]></join>
     </c>
   </and>
 </filter>
'
where COMPONENT = '[tramservercuba$TransportItem.browse].filter' and NAME = 'Поиск в реестре подвижного состава' ^
