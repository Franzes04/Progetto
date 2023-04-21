import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPersona } from 'app/shared/model/persona.model';
import { getEntities } from './persona.reducer';

export const Persona = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const personaList = useAppSelector(state => state.persona.entities);
  const loading = useAppSelector(state => state.persona.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="persona-heading" data-cy="PersonaHeading">
        Personas
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/persona/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Genera un nuovo Persona
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {personaList && personaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Data Nascita</th>
                <th>Email</th>
                <th>Citta</th>
                <th>Eta</th>
                <th>Telefono</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {personaList.map((persona, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/persona/${persona.id}`} color="link" size="sm">
                      {persona.id}
                    </Button>
                  </td>
                  <td>{persona.nome}</td>
                  <td>{persona.cognome}</td>
                  <td>
                    {persona.dataNascita ? <TextFormat type="date" value={persona.dataNascita} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{persona.email}</td>
                  <td>{persona.citta}</td>
                  <td>{persona.eta}</td>
                  <td>{persona.telefono}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/persona/${persona.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Visualizza</span>
                      </Button>
                      <Button tag={Link} to={`/persona/${persona.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Modifica</span>
                      </Button>
                      <Button tag={Link} to={`/persona/${persona.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Elimina</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Personas found</div>
        )}
      </div>
    </div>
  );
};

export default Persona;
