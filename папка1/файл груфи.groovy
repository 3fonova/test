import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import static com.atlassian.jira.issue.IssueFieldConstants.PRIORITY
 
// ID пользовательского поля "Тип задачи"
def typeOfTaskFieldId = "customfield_13841"
 
// Получаем менеджер пользовательских полей
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def typeOfTaskField = customFieldManager.getCustomFieldObject(typeOfTaskFieldId)
 
// Получаем значение поля "Тип задачи"
def typeOfTaskValue = underlyingIssue?.getCustomFieldValue(typeOfTaskField)
 
// Проверяем, выбрано ли значение "ЗНО"
boolean isZNOSelected = typeOfTaskValue != null && (typeOfTaskValue instanceof Collection ?
typeOfTaskValue.any { it.toString() == 'ЗНО' } : typeOfTaskValue.toString() == 'ЗНО')
 
// Получаем поле "Приоритет"
def priorityField = getFieldById(PRIORITY)
 
if (isZNOSelected) {
// Если выбрано ЗНО, делаем поле "Приоритет" недоступным для редактирования
priorityField.setReadOnly(true)
} else {
// Если ЗНО не выбрано, оставляем поле "Приоритет" доступным для редактирования
priorityField.setReadOnly(false)
}
 
// Возвращаем null, так как мы не изменяем значение поля, а только его свойства
return null