package sellcars.service;

import org.springframework.data.jpa.domain.Specification;
import sellcars.models.Advert;
import sellcars.models.Car;

import javax.persistence.criteria.*;
import java.util.List;

public class AdvertSpecifications {

    public static Specification<Advert> orderByDateDesc() {
        return new Specification<Advert>() {
            @Override
            public Predicate toPredicate(Root<Advert> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("date")));
                return criteriaQuery.getRestriction();
            }
        };
    }

    public static Specification<Advert> matchAdvertsByCarList(final List<Car> cars) {
        return new Specification<Advert>() {
            public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Path<Car> group = root.get("car");
                return group.in(cars);
            }
        };
    }

    public static Specification<Advert> selectByDateBetween(String min, String max) {
        return new Specification<Advert>() {
            @Override
            public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("date"), min, max);
            }
        };
    }

    public static Specification<Advert> selectByPriceBetween(double min, double max) {
        return new Specification<Advert>() {
            @Override
            public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("price"), min, max);
            }
        };
    }

    public static Specification<Advert> selectBySoldStatus(boolean isSold) {
        return new Specification<Advert>() {
            @Override
            public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("sold"), isSold);
            }
        };
    }

    public static Specification<Advert> selectOnlyWithPhoto() {
        return new Specification<Advert>() {
            @Override
            public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isNotEmpty(root.get("photos"));
            }
        };
    }

}
