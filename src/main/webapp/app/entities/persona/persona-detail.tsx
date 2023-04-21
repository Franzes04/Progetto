import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './persona.reducer';

export const PersonaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const personaEntity = useAppSelector(state => state.persona.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="personaDetailsHeading">Persona</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{personaEntity.id}</dd>
          <dt>
            <span id="nome">Nome</span>
          </dt>
          <dd>{personaEntity.nome}</dd>
          <dt>
            <span id="cognome">Cognome</span>
          </dt>
          <dd>{personaEntity.cognome}</dd>
          <dt>
            <span id="dataNascita">Data Nascita</span>
          </dt>
          <dd>
            {personaEntity.dataNascita ? <TextFormat value={personaEntity.dataNascita} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{personaEntity.email}</dd>
          <dt>
            <span id="citta">Citta</span>
          </dt>
          <dd>{personaEntity.citta}</dd>
          <dt>
            <span id="eta">Eta</span>
          </dt>
          <dd>{personaEntity.eta}</dd>
          <dt>
            <span id="telefono">Telefono</span>
          </dt>
          <dd>{personaEntity.telefono}</dd>
        </dl>
        <Button tag={Link} to="/persona" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Indietro</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/persona/${personaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Modifica</span>
        </Button>
      </Col>
    </Row>
  );
};

export default PersonaDetail;
