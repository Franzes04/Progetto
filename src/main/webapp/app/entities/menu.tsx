import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/persona">
        Persona
      </MenuItem>
      <MenuItem icon="asterisk" to="/domande">
        Domande
      </MenuItem>
      <MenuItem icon="asterisk" to="/risposta">
        Risposta
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
