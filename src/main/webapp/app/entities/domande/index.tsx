import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Domande from './domande';
import DomandeDetail from './domande-detail';
import DomandeUpdate from './domande-update';
import DomandeDeleteDialog from './domande-delete-dialog';

const DomandeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Domande />} />
    <Route path="new" element={<DomandeUpdate />} />
    <Route path=":id">
      <Route index element={<DomandeDetail />} />
      <Route path="edit" element={<DomandeUpdate />} />
      <Route path="delete" element={<DomandeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DomandeRoutes;
