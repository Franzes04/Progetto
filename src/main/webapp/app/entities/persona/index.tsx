import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Persona from './persona';
import PersonaDetail from './persona-detail';
import PersonaUpdate from './persona-update';
import PersonaDeleteDialog from './persona-delete-dialog';

const PersonaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Persona />} />
    <Route path="new" element={<PersonaUpdate />} />
    <Route path=":id">
      <Route index element={<PersonaDetail />} />
      <Route path="edit" element={<PersonaUpdate />} />
      <Route path="delete" element={<PersonaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PersonaRoutes;
