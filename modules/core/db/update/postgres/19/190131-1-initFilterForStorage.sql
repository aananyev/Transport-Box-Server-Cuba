insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$EquipmentStorageItem.browse].filter', 'Поиск на складе',
'<?xml version="1.0" encoding="UTF-8"?>

<filter>
  <and>
    <c name="aggregateItem.model.aggregateKind" class="ru.itpearls.tramservercuba.entity.AggregateKind" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.aggregateItem.model.aggregateKind = :component$filter.aggregateItem_model_aggregateKind52296]]>
      <param name="component$filter.aggregateItem_model_aggregateKind52296" javaClass="ru.itpearls.tramservercuba.entity.AggregateKind">NULL</param>
    </c>
    <c name="aggregateItem.model.type" class="ru.itpearls.tramservercuba.entity.AggregateType" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.aggregateItem.model.type.id = :component$filter.aggregateItem_model_type37100]]>
      <param name="component$filter.aggregateItem_model_type37100" javaClass="ru.itpearls.tramservercuba.entity.AggregateType">NULL</param>
    </c>
  </and>
</filter>
', TRUE) ^