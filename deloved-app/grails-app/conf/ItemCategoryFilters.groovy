import ru.deloved.CategoryType

class ItemCategoryFilters {

	def filters = {
		all(controller: '(item|goods|services)', action: '*') {
			before = {
				if (params.categoryTypeCode) {
					params.categoryType = CategoryType.findByCode(params.categoryTypeCode)
				}
			}
			after = { Map model ->

			}
			afterView = { Exception e ->

			}
		}
	}
}
