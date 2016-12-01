package barbershop.dao;

import java.util.List;

public interface CasherDao <Vip> extends BaseDao<Vip> {

	@Override
	default Vip get(Class<Vip> entityclass, String primary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default String save(Vip entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default String delete(Vip entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default String update(Vip entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default List<Vip> executeQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	 

}
