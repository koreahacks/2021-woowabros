import styled from "styled-components";

import IMAGE_SRC from "../../ImageSrc";

const HeaderWrapper = styled.div`
  display: flex;
  justify-content: space-around;
`;

const LogoImg = styled.img`
  width: 4.125rem;
  height: 4.125rem;
`;

const HeaderMessage = styled.div`
  color: #aa8fbf;
  font-size: 1.5rem;
  font-weight: 800;
  display: flex;
  align-items: center;
`;

const Header = () => {
  return (
    <HeaderWrapper>
      <LogoImg src={IMAGE_SRC.UNICORN} alt="app-logo" />
      <HeaderMessage>
        전국에서 하는 대학생활
        <br />
        Uniconn
      </HeaderMessage>
    </HeaderWrapper>
  );
};

export default Header;
