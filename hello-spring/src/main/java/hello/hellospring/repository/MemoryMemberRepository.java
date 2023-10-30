package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
import java.util.stream.Stream;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //Collection<Member> members = store.values();
        //Store 에서 모든 맴버 값을 얻어와서 members에 저장

        //모든 뱁머를 스트림으로 변환하여 이름이 주어진 name과
        // 일치하는 맴버만 필터링
        //Stream<Member> memberStream = members.stream()
        //        .filter(member -> member.getName().equals(name));

        //필터링된 맴버 중 어떤 하나를 찾아 반환하다.
        //Optional<Member> result = memberStream.findAny();
        //return result;

        //위의 내용을 축약하면
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore(){
        store.clear();
    };
}
