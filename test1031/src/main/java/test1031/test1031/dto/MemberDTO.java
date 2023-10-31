package test1031.test1031.dto;

public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());

        return memberDTO;
    }
}
//DTO란 : 데이터 전송 객체(Data Transfer Object), 프로세스 간에 데이터를 전달하는 객체 => Client <-> Controller 사이,
//Controller <-> Service 사이 처럼 단순히 데이터 전달 목적.