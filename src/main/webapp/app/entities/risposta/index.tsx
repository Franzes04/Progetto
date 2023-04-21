import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Risposta from './risposta';
import RispostaDetail from './risposta-detail';
import RispostaUpdate from './risposta-update';
import RispostaDeleteDialog from './risposta-delete-dialog';

const RispostaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Risposta />} />
    <Route path="new" element={<RispostaUpdate />} />
    <Route path=":id">
      <Route index element={<RispostaDetail />} />
      <Route path="edit" element={<RispostaUpdate />} />
      <Route path="delete" element={<RispostaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RispostaRoutes;
