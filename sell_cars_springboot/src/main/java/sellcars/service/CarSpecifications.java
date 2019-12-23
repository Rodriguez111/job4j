package sellcars.service;

import org.springframework.data.jpa.domain.Specification;
import sellcars.models.Car;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CarSpecifications {

    public static Specification<Car> orderById() {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
                return criteriaQuery.getRestriction();
            }
        };
    }


    public static Specification<Car> selectByCarBrand(String brand) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("carBrand").get("carBrand"), brand);
            }
        };
    }

    public static Specification<Car> selectByCarModel(String model) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("carModel"), model);
            }
        };
    }

    public static Specification<Car> selectByBodyType(String type) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("bodyType").get("bodyType"), type);
            }
        };
    }

    public static Specification<Car> selectByEngine(String engine) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("engine").get("engineType"), engine);
            }
        };
    }

    public static Specification<Car> selectByEngineVolumeBetween(double min, double max) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("engineVolume"), min, max);
            }
        };
    }

    public static Specification<Car> selectByTransmission(String transmission) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("transmission").get("transmissionType"), transmission);
            }
        };
    }

    public static Specification<Car> selectByMileageBetween(int min, int max) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("mileage"), min, max);
            }
        };
    }

    public static Specification<Car> selectByYearBetween(int min, int max) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("year"), min, max);
            }
        };
    }

    public static Specification<Car> selectByVin(String vin) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("vin"), vin);
            }
        };
    }

    public static Specification<Car> selectByColor(String color) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("color"), color);
            }
        };
    }
}
