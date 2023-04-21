import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRisposta } from 'app/shared/model/risposta.model';
import { getEntities } from './risposta.reducer';

export const Risposta = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const rispostaList = useAppSelector(state => state.risposta.entities);
  const loading = useAppSelector(state => state.risposta.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="risposta-heading" data-cy="RispostaHeading">
        Rispostas
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/risposta/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Genera un nuovo Risposta
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {rispostaList && rispostaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Risposte</th>
                <th>Domande</th>
                <th>Persona</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {rispostaList.map((risposta, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/risposta/${risposta.id}`} color="link" size="sm">
                      {risposta.id}
                    </Button>
                  </td>
                  <td>{risposta.risposte}</td>
                  <td>{risposta.domande ? <Link to={`/domande/${risposta.domande.id}`}>{risposta.domande.descrizione}</Link> : ''}</td>
                  <td>{risposta.persona ? <Link to={`/persona/${risposta.persona.id}`}>{risposta.persona.nome}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/risposta/${risposta.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Visualizza</span>
                      </Button>
                      <Button tag={Link} to={`/risposta/${risposta.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Modifica</span>
                      </Button>
                      <Button tag={Link} to={`/risposta/${risposta.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Elimina</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Rispostas found</div>
        )}
      </div>
    </div>
  );
};

export default Risposta;
