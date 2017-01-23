package ru.deloved

import grails.transaction.Transactional

@Transactional
class CategoryService {

	private Map<Long, Category> allItems = new Hashtable<Long, Category>()
	private Map<Long, Category> parentItems = new Hashtable<Long, Category>()
	private Map<Long, List<Category>> childs = new Hashtable<>()
	private Map<Long, List> tree = new Hashtable<>()

	public void init() {
		Category.findAll().each { Category it ->
			allItems.put(it.id, it)
			if (it.parent) {
				List<Category> list = childs.get(it.parent.id)
				if (list == null) {
					list = new ArrayList<>()
					childs.put(it.parent.id, list)
				}
				list.add(it)
			} else {
				parentItems.put(it.id, it)
			}
		}
		childs.values().each { List<Category> list ->
			list.sort { Category r1, Category r2 ->
				r1.name.compareTo(r2.name)
			}
		}
		parentItems.values().each { Category it ->
			def list = []
			fillCategoriesTree(it, list)
			tree.put(it.id, list)
		}
	}

	private void fillCategoriesTree(Category r, ArrayList res) {
		def list = []
		getChilds(r.id).each { ch ->
			fillCategoriesTree(ch, list)
		}
		if (list.isEmpty()) {
			res.add([id: r.id, text: r.name])
		} else {
			res.add([id: r.id, text: r.name, children: list])
		}
	}

	public Category getCategory(Long id) {
		id ? allItems.get(id) : null
	}

	public List<Category> getChilds(Long id) {
		id ? childs.get(id) ?: [] : []
	}

	public List<Category> getChilds(Category item) {
		item ? childs.get(item.id) ?: [] : []
	}

	public Collection<Category> getParentsCategories() {
		parentItems.values()
	}

	public List getTree(Long id) {
		if (id) {
			tree.get(id)
		} else {
			[]
		}
	}

	private void loadParentsTop(Category el, ArrayList parents) {
		if (el?.parent?.parent) {
			parents.add(el.parent)
			loadParentsTop(el.parent, parents)
		}
	}


	public List<Category> getAccountCategories(Account account) {
		List<Category> accCatList = []
		List<Category> parents = []

		def loader
		loader = { Category c, List<Category> list ->
			Category cat = getCategory(c.id)
			if (cat) {
				list.add(cat)
				getChilds(c).each { Category it ->
					loader(it, list)
				}
			}
		}

		AccountCategory.findAllByAccount(account).each {
			Category c = getCategory(it.categoryId)
			loadParentsTop(c, parents)
			if (c) {
				accCatList.add(c)
				loader(c, accCatList)
			}
		}
		accCatList.addAll(parents.unique())
		accCatList.unique()
	}

	public void save(Account account, ArrayList<Long> catList){
		for (int i=0; i<catList.size(); i++) {
			new AccountCategory(account: account, category: Category.load(catList[i])).save(flush: true)
		}
	}

	public void update(Account account, ArrayList<Long> categories) {

	}
}
