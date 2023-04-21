import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './risposta.reducer';

export const RispostaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const rispostaEntity = useAppSelector(state => state.risposta.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rispostaDetailsHeading">Risposta</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{rispostaEntity.id}</dd>
          <dt>
            <span id="risposte">Risposte</span>
          </dt>
          <dd>{rispostaEntity.risposte}</dd>
          <dt>Domande</dt>
          <dd>{rispostaEntity.domande ? rispostaEntity.domande.descrizione : ''}</dd>
          <dt>Persona</dt>
          <dd>{rispostaEntity.persona ? rispostaEntity.persona.nome : ''}</dd>
        </dl>
        <Button tag={Link} to="/risposta" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Indietro</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/risposta/${rispostaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Modifica</span>
        </Button>
      </Col>
    </Row>
  );
};

export default RispostaDetail;
