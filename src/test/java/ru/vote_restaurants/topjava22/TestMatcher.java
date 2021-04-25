package ru.vote_restaurants.topjava22;

import java.util.function.BiConsumer;
import static org.assertj.core.api.Assertions.assertThat;

public class TestMatcher<T> {
    private final Class<T> clazz;
    private final BiConsumer<T, T> assertion;
    private final BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion;

    private TestMatcher(Class<T> clazz, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion) {
        this.clazz = clazz;
        this.assertion = assertion;
        this.iterableAssertion = iterableAssertion;
    }

    public static <T> TestMatcher<T> usingAssertions(Class<T> clazz, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion) {
        return new TestMatcher<>(clazz, assertion, iterableAssertion);
    }

    public static <T> TestMatcher<T> usingFieldsWithIgnoringAssertions(Class<T> clazz, String... fieldsToIgnore) {
        return usingAssertions(clazz,
                (a, e) -> assertThat(a).isEqualToIgnoringGivenFields(e, fieldsToIgnore),
                (a, e) -> assertThat(a).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(e));
    }

    public void assertMatch(T actual, T expected) {
        assertion.accept(actual, expected);
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        iterableAssertion.accept(actual, expected);
    }
}
